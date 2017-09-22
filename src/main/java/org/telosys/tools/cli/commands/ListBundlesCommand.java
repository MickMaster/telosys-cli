/**
 *  Copyright (C) 2015-2017  Telosys project org. ( http://www.telosys.org/ )
 *
 *  Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.telosys.tools.cli.commands;

import java.util.List;

import jline.console.ConsoleReader;

import org.telosys.tools.cli.Command;
import org.telosys.tools.cli.Environment;
import org.telosys.tools.cli.commons.BundlesFilter;
import org.telosys.tools.commons.TelosysToolsException;

public class ListBundlesCommand extends Command {
	
	/**
	 * Constructor
	 * @param out
	 */
	public ListBundlesCommand(ConsoleReader consoleReader, Environment environment) {
		super(consoleReader, environment);
	}

	@Override
	public String getName() {
		return "lb";
	}

	@Override
	public String getShortDescription() {
		return "List Bundles" ;
	}

	@Override
	public String getDescription() {
		return "List the installed bundles";
	}
	
	@Override
	public String getUsage() {
		return "lb [name-part1 name-part2 ...]";
	}
	
	@Override
	public String execute(String[] args) {
		if ( checkHomeDirectoryDefined() ) {
			listBundles(args);
		}
		return null ;
	}

	private void listBundles(String[] commandArgs) {
//		List<String> criteria = BundlesFilter.buildCriteriaFromArgs(args);
//		TelosysProject telosysProject = getTelosysProject();
//		try {
//			// get all installed bundles
//			List<String> bundles = telosysProject.getInstalledBundles();
//			// filter with criteria if any
//			List<String> filteredBundles = BundlesFilter.filter(bundles, criteria);
//			return printBundles(filteredBundles);
//			
//		} catch (TelosysToolsException e) {
//			printError(e);
//		}
//		return null ;
		
		try {
			List<String> bundleNames = BundlesFilter.getExistingBundles(getTelosysProject(), commandArgs);	
			if ( bundleNames.size() > 0 ) {
				print( bundleNames.size() + " bundle(s) :") ;
				printList(bundleNames) ;
			}
			else {
				print("No bundle found.") ;
			}
		} catch (TelosysToolsException e) {
			printError(e);
		}
	}
	
//	/**
//	 * Prints the given bundles
//	 * @param bundles
//	 * @return
//	 */
//	private String printBundles(List<String> bundles) {
//		StringBuffer sb = new StringBuffer();
//		if ( bundles != null && bundles.size() > 0 ) {
//			//appendLine(sb, "Bundles installed in the current project : ");
//			for ( String s : bundles ) {
//				appendLine(sb, " . " + s);
//			}
//		}
//		else {
//			appendLine(sb, "No bundle found.");
//		}
//		return sb.toString();
//	}

}
